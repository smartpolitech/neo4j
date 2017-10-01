import { Room } from './room.model';
import { Device } from './device.model';

export interface Building {
    id: string;
    rooms: Room[];
    devices: Device[];
}
