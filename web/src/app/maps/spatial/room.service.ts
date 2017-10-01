///<reference path="../../../../typings/globals/mapbox-gl/index.d.ts"/>
import { Injectable } from '@angular/core';
import * as mapboxgl from 'mapbox-gl';
import { Room } from './room.model';
import { Observable } from 'rxjs/Rx';
import { MapHolder } from '../map.holder';
import { RoomInfoBuilder } from './room.info.builder';
import { roomFillsSelected } from './rooms.layers';
import {
    roomsSourceId, roomsSource,
    roomFills, roomFillsHover
} from './rooms.layers';

@Injectable()
export class RoomService {
    private map: mapboxgl.Map;

    constructor(private mapHolder: MapHolder,
        private roomInfoBuilder: RoomInfoBuilder) {
        mapHolder.getMap().subscribe(map => this.map = map);
    }

    public initialLoad() {
        this.addSourceToMap();
        this.addLayersToMap();
        this.highlightRoomOnHover();
        this.showRoomInfoOnClick();
    }

    private addSourceToMap() {
        this.map.addSource(roomsSourceId, roomsSource);
    }

    private addLayersToMap() {
        this.map.addLayer(roomFills);
        this.map.addLayer(roomFillsHover);
        this.map.addLayer(roomFillsSelected);
    }

    private highlightRoomOnHover() {
        this.map.on('mousemove', 'roomFills', event => {
            this.map.setFilter('roomFillsHover', ['==', 'id', event.features[0].properties.id]);
            this.map.getCanvas().style.cursor = 'pointer';
        });

        this.map.on('mouseleave', 'roomFills', event => {
            this.map.setFilter('roomFillsHover', ['==', 'id', '']);
            this.map.getCanvas().style.cursor = '';
        });
    }

    private showRoomInfoOnClick() {
        this.map.on('click', 'roomFills', event => {
            this.markSelectedRoom(event.features[0].properties.id);
            new mapboxgl.Popup({closeOnClick: false})
                .setLngLat(event.lngLat)
                .setHTML(this.roomInfoBuilder.buildRoomInfo(event.features[0].properties))
                .on('close', e => this.unmarkSelectedRoom())
                .addTo(this.map);
        });
    }

    private markSelectedRoom(id: string) {
        this.map.setFilter('roomFillsSelected', ['==', 'id', id]);
    }

    private unmarkSelectedRoom() {
        this.map.setFilter('roomFillsSelected', ['==', 'id', '']);
    }

    public loadRooms(rooms: Room[]) {
        this.setRoomsSource(this.createFeatureCollection(rooms))
    }

    private setRoomsSource(features: GeoJSON.FeatureCollection<GeoJSON.GeometryObject>) {
        (<mapboxgl.GeoJSONSource>this.map.getSource(roomsSourceId)).setData(features);
    }

    public selectFloor(floorId: string) {
        this.filterRoomsByFloor(floorId);
    }

    private filterRoomsByFloor(floorId: string) {
        this.map.setFilter(roomFills.id, ['==', 'floorId', floorId]);
    }

    private createFeatureCollection(rooms: Room[]): GeoJSON.FeatureCollection<GeoJSON.GeometryObject> {
        let features = rooms.map(r => this.createFeature(r));
        return {
            type: 'FeatureCollection',
            features: features
        };
    }

    private createFeature(room: Room): any {
        const points = this.parseWKTPolygon(room.location);
        return {
            type: 'Feature',
            properties: room,
            geometry: {
                type: 'Polygon',
                coordinates: [points]
            }
        };
    }

    private parseWKTPolygon(wkt): Number[] {
        const points = [];
        const wktPolygonPoints = wkt.slice(10, -3).split(',');

        wktPolygonPoints.forEach(wktPoint => {
            const wktXYString = wktPoint.trim().split(' ');
            const wktXYNum = [Number(wktXYString[0]), Number(wktXYString[1])];
            points.push(wktXYNum);
        });
        return points;
    }
}
