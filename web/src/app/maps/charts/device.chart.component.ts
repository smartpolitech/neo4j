import { Component, OnInit, Input, OnChanges, SimpleChanges, Output, EventEmitter, ElementRef, ChangeDetectorRef } from '@angular/core';
import { COLORS } from './device.chart.colors';
import { element } from 'protractor';
import { DeviceLogData } from './device.log.model';

@Component({
    selector: 'line-chart',
    templateUrl: './device.chart.component.html',
    styleUrls: ['./device.chart.component.css']
})
export class DeviceChartComponent implements OnInit, OnChanges {
    @Input() deviceDescription;
    @Input() xAxisLabels: Array<string>;
    @Input() datasets: Array<DeviceLogData>;
    @Output() close: EventEmitter<void> = new EventEmitter<void>();

    private numDataSets;

    // Input for graph
    private colors = COLORS;
    private legend: boolean = true;
    private type = 'line';
    private graphOptions: any;
    private graphDatasets: Array<DeviceLogData>;

    private emptyDataset = { data: [], label: ''};
    public constructor(private changeDetector: ChangeDetectorRef) { }

    ngOnInit() {
        this.buildOptions();
        this.numDataSets = this.datasets.length;
    }

    ngOnChanges(changes: SimpleChanges) {
        if (changes['datasets']) {
            this.buildDatasets();
        }

        if (changes['deviceDescription']) { 
            this.buildOptions();
        }
    }

    private buildDatasets() {
        this.datasets = this.datasets ? this.datasets : [];
        for (let i = this.datasets.length; i < this.numDataSets; i++) {
            this.datasets.push(this.emptyDataset);
        }
        this.graphDatasets = this.datasets;
    }

    private buildOptions(): any {
        this.graphOptions = {
            responsive: true,
            legend: {
                position: 'bottom'
            },
            title: {
                display: true,
                padding: 10,
                text: `Información del sensor - ${this.deviceDescription}`
            }
        };
    }

    private closePanel() {
        this.close.emit();
    }
}
