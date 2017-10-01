import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { MapComponent } from './maps/map.component';
import { SpatialService } from './maps/spatial/spatial.service';
import { LayersControlComponent } from './maps/layers-control/layers.control.component';
import { MapHolder } from './maps/map.holder';
import { DeviceService } from './maps/spatial/device.service';
import { RoomService } from './maps/spatial/room.service';
import { RoomInfoBuilder } from './maps/spatial/room.info.builder';
import { ChartsModule } from 'ng2-charts';
import { DeviceChartComponent } from './maps/charts/device.chart.component';

@NgModule({
  declarations: [
    AppComponent,
    MapComponent,
    LayersControlComponent,
    DeviceChartComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ChartsModule
  ],
  providers: [
    SpatialService,
    MapHolder,
    DeviceService,
    RoomService,
    RoomInfoBuilder,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
