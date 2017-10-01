import { Injectable } from '@angular/core';
import { Http, URLSearchParams, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/Rx';
import { Room } from './room.model';
import { Building } from './building.model';

@Injectable()
export class SpatialService {

    constructor(private http: Http) { }

    findBuildingsInArea(lngLat: mapboxgl.LngLat): Observable<Building[]> {
        const params = new URLSearchParams();
        params.set('lat', lngLat.lat.toString());
        params.set('lon', lngLat.lng.toString());
        params.set('distanceInKm', '0.15');

        return this.http.get('/smart-politech/api/spatial/buildings', { search: params }).map(r => r.json());
    }
}
