package unex.epcc.lruiz.devices.data;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import unex.epcc.lruiz.domain.DeviceData;
import unex.epcc.lruiz.domain.GraphData;

@Component
public class DeviceLogParser {

	public DeviceData parse(Results results) {
		if(isEmpty(results.getResults()) || isEmpty(results.getResults().get(0).getSeries())) {
			return DeviceData.builder().labels(emptyList()).data(Arrays.asList(GraphData.builder().data(emptyList()).build())).build();
		}
		Serie serie = results.getResults().get(0).getSeries().get(0);
		
		Map<DataType, Integer> indexes = getIndexes(serie, DataType.values());
		
		if(!indexes.containsKey(DataType.time)) {
			throw new IllegalArgumentException(String.format("Missing column 'TIME' from device log."));
		}
		
		return DeviceData.builder()
				.data(buildGraphData(serie, indexes))
				.labels(buildLabels(serie, indexes.get(DataType.time)))
				.build();
	}
	
	private Map<DataType, Integer> getIndexes(Serie serie, DataType ...columns) {
		return asList(columns)
				.stream()
				.collect(toMap(column -> column, column -> getIndex(serie, column)))
				.entrySet()
				.stream()
				.filter(entry -> entry.getValue() >= 0)
				.collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
	
	private int getIndex(Serie serie, DataType column) {
		return serie.getColumns().indexOf(column.name());
	}
	
	private List<GraphData> buildGraphData(Serie serie, Map<DataType, Integer> indexes) {
		Map<DataType, List<String>> data = initMap(indexes.keySet());
		
		serie.getValues().forEach(point -> {
			indexes.entrySet().stream().filter(this::isNoTime).forEach(index -> {
				DataType columnName = index.getKey();
				Integer columnIndex = index.getValue();
				data.get(columnName).add(point.get(columnIndex).toString());
			});
		});
		
		return data.entrySet().stream().map(this::toGraphData).collect(toList());
	}
	
	private Map<DataType, List<String>> initMap(Set<DataType> keys) {
		return keys.stream().filter(k -> !k.equals(DataType.time)).collect(toMap(k -> k, __ -> new ArrayList<>()));
	}
	
	private List<String> buildLabels(Serie serie, int timeIndex) {
		return serie.getValues().stream().map(point -> toFormattedTime(point.get(timeIndex))).collect(toList());
	}
	
	private String toFormattedTime(String dateTime) {
		return LocalDateTime.parse(dateTime, ISO_DATE_TIME).format(DateTimeFormatter.ofPattern("HH:mm"));
	}
	
	private GraphData toGraphData(Map.Entry<DataType, List<String>> entry) {
		return GraphData.builder().label(entry.getKey().getLabel()).data(entry.getValue()).build();
	}
	
	private boolean isNoTime(Map.Entry<DataType, Integer> entry) {
		return isNoTime(entry.getKey());
	}
	
	private boolean isNoTime(DataType key) {
		return !key.equals(DataType.time);
	}
}
