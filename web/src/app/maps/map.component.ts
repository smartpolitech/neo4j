import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import * as mapboxgl from 'mapbox-gl';

import { SpatialService } from './spatial/spatial.service';
import { Room } from './spatial/room.model';
import { MapHolder } from './map.holder';
import { FLOOR_PLANS, DEVICES } from './layer.control.data';
import { DeviceService } from './spatial/device.service';
import { Selected } from './layers-control/layers.control.component';
import { element } from 'protractor';
import { RoomService } from './spatial/room.service';
import { Building } from './spatial/building.model';
import { Device } from './spatial/device.model';
import { fakeData } from './charts/device.chart.fake.data';
import { DeviceLogData } from './charts/device.log.model';

@Component({
    selector: 'map',
    templateUrl: './map.component.html',
    styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {
    private map: mapboxgl.Map;
    private currentCenter: mapboxgl.LngLat;

    // Input for layers control
    private floorPlans = FLOOR_PLANS;
    private selectedFloorId: string = FLOOR_PLANS[0].id;

    private deviceTypes = DEVICES;
    private selectedDeviceTypes: { [deviceType: string]: boolean } = {};

    // Input for charts
    private chartPanelsByDeviceType = {
        temperature: 'temperature-chart',
        water: 'water-chart',
        power: 'power-chart'
    }

    private deviceDescription = '';
    private temperatureDatasets: Array<DeviceLogData> = [{label: '', data: []}, {label: '', data: []}];
    private waterDatasets: Array<DeviceLogData> = [{label: '', data: []}];
    private powerDatasets: Array<DeviceLogData> = [{label: '', data: []}];
    private xAxisLabels = [];

    constructor(private changeDetector: ChangeDetectorRef,
        private mapHolder: MapHolder,
        private spatialService: SpatialService,
        private deviceService: DeviceService,
        private roomService: RoomService) { }

    ngOnInit() {
        this.initSelectedFloor();
        this.initSelectedDeviceTypes();

        (mapboxgl as any).accessToken = 'pk.eyJ1Ijoic21hcnRwb2xpdGVjaCIsImEiOiJjajR3Z2MzZmQxMGQ4MnFxbmNzbm5wdzZ5In0.IMyIU15yOBsq3MWiELnAKg';
        this.map = new mapboxgl.Map({
            container: 'map',
            style: 'mapbox://styles/smartpolitech/cj4wgoch52e0g2rph2yu56xyc',
            center: [-6.342155, 39.478964],
            zoom: 18
        });
        this.map.addControl(new mapboxgl.NavigationControl());
        this.onMapLoad();
        this.onMapClick();
        this.onMapMove();
        this.mapHolder.setMap(this.map);
        this.onDeviceClicked();
    }

    private initSelectedFloor() {
        this.selectedFloorId = this.floorPlans[0].id;
    }

    private initSelectedDeviceTypes() {
        this.deviceTypes.forEach(element => {
            this.selectedDeviceTypes[element.id] = false;
        });
    }

    onMapLoad() {
        this.map.on('load', () => {
            this.roomService.initialLoad();
            this.loadFeaturesInArea();
            this.currentCenter = this.map.getCenter();
        });
    }

    private loadFeaturesInArea() {
        this.spatialService.findBuildingsInArea(this.map.getCenter()).subscribe(buildings => {
            let rooms: Room[] = [];
            let devices: Device[] = [];

            buildings.forEach(building => {
                rooms = rooms.concat(building.rooms);
                devices = devices.concat(building.devices);
            });

            this.roomService.loadRooms(rooms);
            this.roomService.selectFloor(this.selectedFloorId);
            this.deviceService.loadDevices(devices);
        });
    }

    onMapClick() {
        this.map.on('click', event => {
            this.cleanPopups();
        });
    }

    onMapMove() {
        this.map.on('dragend', () => {
            this.loadFeaturesInArea();
        });
    }

    onSelectFloor(selectedFloor: Selected) {
        this.cleanPopups();
        this.closeDeviceChartPanels();
        this.selectedFloorId = selectedFloor.id;
        this.roomService.selectFloor(this.selectedFloorId);
        this.deviceService.updateDevices(this.selectedFloorId, this.selectedDeviceTypes);
    }

    onSelectDeviceType(selectedDeviceType: Selected) {
        this.selectedDeviceTypes[selectedDeviceType.id] = selectedDeviceType.checked;
        this.deviceService.updateDevices(this.selectedFloorId, this.selectedDeviceTypes);
    }

    onDeviceClicked() {
        this.deviceService.getClickedDevice().subscribe(device => {
            this.cleanPopups();
            this.deviceService.getDeviceLog(device.id).subscribe(deviceLog => {
                this.xAxisLabels = deviceLog.labels;
                this.deviceDescription = device.description;
                this.changeDetector.detectChanges();
                if(device.type === 'temperature') {
                    this.temperatureDatasets = deviceLog.data;
                } else if (device.type === 'water') {
                    this.waterDatasets = deviceLog.data;
                } else if (device.type === 'power') {
                    this.powerDatasets = deviceLog.data;
                }
                this.changeDetector.detectChanges();
                this.showDeviceChartPanel(this.chartPanelsByDeviceType[device.type]);
            });
        });
    }

    private cleanPopups() {
        let popupCloseButton = document.getElementsByClassName('mapboxgl-popup-close-button');
        if (popupCloseButton[0]) (<HTMLElement>popupCloseButton[0]).click();
    }

    public showDeviceChartPanel(chartPanelId: string) {
        let panel = document.getElementById(chartPanelId);
        panel.classList.add('left-panel-show');
        panel.classList.remove('left-panel-hide');
    }

    public closeDeviceChartPanels() {
        Object.keys(this.chartPanelsByDeviceType)
            .map(deviceType => this.chartPanelsByDeviceType[deviceType])
            .forEach(chartPanelId => this.closeChartPanel(chartPanelId))
    }

    public closeChartPanel(chartPanelId: any) {
        let panel = document.getElementById(chartPanelId);
        panel.classList.add('left-panel-hide');
        panel.classList.remove('left-panel-show');
    }
}
