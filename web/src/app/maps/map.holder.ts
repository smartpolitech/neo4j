import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Rx';

import * as mapboxgl from 'mapbox-gl';

@Injectable()
export class MapHolder {

    private map: Subject<mapboxgl.Map> = new Subject();

    setMap(map: mapboxgl.Map) {
       this.map.next(map);
       this.map.complete();
    }

    getMap() {
        return this.map;
    }
}
