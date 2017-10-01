///<reference path="../../../../typings/globals/mapbox-gl/index.d.ts"/>
import * as mapboxgl from 'mapbox-gl';

// Sources
export const roomsSourceId = 'rooms';

export const roomsSource: mapboxgl.GeoJSONSourceRaw = {
    type: 'geojson',
    data: {
        type: 'FeatureCollection',
        features: []
    }
};

// Layers
export const roomFills: mapboxgl.Layer = {
    id: 'roomFills',
    type: 'fill',
    source: roomsSourceId,
    layout: {},
    paint: {
        'fill-opacity': 0
    },
    filter: ['==', 'floorId', '']
};

export const roomFillsHover: mapboxgl.Layer = {
    id: 'roomFillsHover',
    type: 'fill',
    source: roomsSourceId,
    layout: {},
    paint: {
        'fill-color': '#b3bbc9',
        'fill-opacity': 0.3
    },
    filter: ['==', 'id', '']
};

export const roomFillsSelected: mapboxgl.Layer = {
    id: 'roomFillsSelected',
    type: 'fill',
    source: roomsSourceId,
    layout: {},
    paint: {
        'fill-color': '#b3bbc9',
        'fill-opacity': 0.5
    },
    filter: ['==', 'id', '']
};

