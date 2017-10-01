///<reference path="../../../../typings/globals/mapbox-gl/index.d.ts"/>
import { Injectable } from '@angular/core';
import * as mapboxgl from 'mapbox-gl';
import { Room } from '../spatial/room.model';
import { MapHolder } from '../map.holder';

@Injectable()
export class RoomInfoBuilder {

    public buildRoomInfo(room: Room): string {
        return '<div>' +
            '<h4>Información de la sala</h4>' +
            '<div>' +
            '	<ul>' +
            '	<li><strong>Identificador: </strong>' + room.id + '</li>' +
            '	<li><strong>Nombre: </strong>' + room.name + '</li>' +
            '	<li><strong>Tipo de sala: </strong>' + room.type + '</li>' +
            '	<li><strong>Area: </strong>' + room.area + '</li>' +
            '	</ul>' +
            '</div>' +
            '</div>';
    }
}
