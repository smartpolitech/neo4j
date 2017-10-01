export interface DeviceLog {
    description: string;
    labels: Array<string>;
    data:  Array<DeviceLogData>;
}

export interface DeviceLogData {
    label: string;
    data: Array<string>;
}
