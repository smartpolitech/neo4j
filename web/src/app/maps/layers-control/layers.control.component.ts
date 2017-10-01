///<reference path="../../../../typings/globals/mapbox-gl/index.d.ts"/>
///<reference path="../../../../typings/globals/geojson/index.d.ts"/>
import * as mapboxgl from 'mapbox-gl';

import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { LayerInfo } from './layer.model';

@Component({
    selector: 'layers-control',
    templateUrl: './layers.control.component.html',
    styleUrls: ['./layers.control.component.css']
})
export class LayersControlComponent implements OnInit {
    @Input() map: mapboxgl.Map;
    @Input() layersInfo: LayerInfo[];
    @Input() overlaysInfo: LayerInfo[];

    @Output() selectedBaseLayer = new EventEmitter<Selected>();
    @Output() selectedOverlay = new EventEmitter<Selected>();

    private collapsed = 'leaflet-control-layers leaflet-control';
    private expanded = 'leaflet-control-layers leaflet-control leaflet-control-layers-expanded';

    private containerClass = this.collapsed;

    ngOnInit() {
        this.map.on('load', () => {
            this.updateBaseLayers(this.layersInfo[0].id);
        });
    }

    expand() {
        this.containerClass = this.expanded;
    }

    collapse() {
        this.containerClass = this.collapsed;
    }

    onLayerChange(event: any) {
        this.updateBaseLayers( event.target['value']);
        this.selectedBaseLayer.emit({ id: event.target['value'], checked: event.target['checked'] });
    }

    private updateBaseLayers(selectedLayerId: string) {
        this.layersInfo.forEach(layer => {
            if (layer.id === selectedLayerId) {
                this.displayLayer(layer.id);
            } else {
                this.hideLayer(layer.id);
            }
        });
    }

    onOverlaySelect(event: Event) {
        this.selectedOverlay.emit({ id: event.target['value'], checked: event.target['checked'] });
    }

    private hideLayer(layerId: string) {
        this.map.setLayoutProperty(layerId, 'visibility', 'none');
    }

    private displayLayer(layerId: string) {
        this.map.setLayoutProperty(layerId, 'visibility', 'visible');
    }
}

export interface Selected {
    id: string;
    checked: boolean;
}