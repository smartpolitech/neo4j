import { Device } from './device.model';

export interface Room {
    id: string;
    type: string;
    name: string;
    area: string;
    location: string;
    floorId: string;
    devices: Device[];
}
