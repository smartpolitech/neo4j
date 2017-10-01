///<reference path="../../../../typings/globals/mapbox-gl/index.d.ts"/>
import { Injectable, EventEmitter, Output } from '@angular/core';
import * as mapboxgl from 'mapbox-gl';
import { MapHolder } from '../map.holder';
import { Device } from './device.model';
import { Observable, Subject } from 'rxjs/Rx';
import { Http } from '@angular/http';
import { DeviceLog } from '../charts/device.log.model';

@Injectable()
export class DeviceService {
    private map: mapboxgl.Map;
    private divSize = 30; // must match the size in styles.css .device

    private deviceClicked: Subject<Device> = new Subject<Device>();

    constructor(private mapHolder: MapHolder,
                private http: Http) {
        mapHolder.getMap().subscribe(map => this.map = map);
    }

    public getClickedDevice(): Subject<Device> {
        return this.deviceClicked;
    }

    public loadDevices(devices: Device[]) {
        this.createMarkersForDevices(devices);
    }

    public getDeviceLog(deviceId: string): Observable<DeviceLog> {
        return this.http.get(`/smart-politech/api/devices/${deviceId}/data`).map(r => r.json());
    }

    private createMarkersForDevices(devices: Device[]) {
        devices.filter(d => !this.isPresent(d)).forEach(device => {
            if (device.location !== 'null' && device.enabled) {
                const el = this.createDiv(device, this.divSize);

                el.addEventListener('click', event => {
                    this.deviceClicked.next(device);
                    event.stopPropagation();
                });

                new mapboxgl.Marker(el, { offset: [-this.divSize / 2, -this.divSize / 2] })
                    .setLngLat(this.parseWKTPoint(device.location))
                    .addTo(this.map);
            }
        });
    }

    private isPresent(device: Device): boolean {
        if (document.getElementById(device.id)) {
            return true;
        }
        return false;
    }

    private parseWKTPoint(wkt): number[] {
        const point = wkt.slice(7, -1).split(' ');
        return point;
    }

    private createDiv(device: Device, size: number): HTMLDivElement {
        const el = document.createElement('div');
        el.setAttribute('id', device.id);
        el.setAttribute('name', device.type);
        el.setAttribute('floor', device.floorId);
        el.classList.add('device', 'invisible', device.type);
        return el;
    }

    public updateDevices(selectedFloorId: string, selectedDevices: { [deviceType: string]: boolean }) {
        Object.keys(selectedDevices)
            .forEach(deviceType => this.updateDevicesByType(selectedFloorId, deviceType, selectedDevices[deviceType]));
    }

    private updateDevicesByType(floorId: string, deviceType: string, isSelected: boolean) {
        const devices = document.getElementsByName(deviceType);
        for (let i = 0; i < devices.length; i++) {
            const isOnFloor = devices[i].getAttribute('floor') === floorId;
            this.setDeviceVisibility(devices[i], isOnFloor && isSelected)
        }
    }

    private setDeviceVisibility(deviceEl: HTMLElement, isVisible: boolean) {
        if (isVisible) {
            deviceEl.classList.add('visible');
        } else {
            deviceEl.classList.remove('visible');
        }
    }
}
