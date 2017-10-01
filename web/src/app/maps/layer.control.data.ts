import { LayerInfo } from './layers-control/layer.model';

// The IDs match name of the layer in mapbox style and id of the floor in neo4j
export const FLOOR_PLANS: LayerInfo[] = [{
    id: 'P00',
    displayName: 'Planta Baja',
    checked: true
}, {
    id: 'P01',
    displayName: 'Primera Planta',
    checked: false
}];


export const DEVICES: LayerInfo[] = [{
    id: 'temperature',
    displayName: 'Sensores temperatura',
    checked: false
}, {
    id: 'water',
    displayName: 'Contadores agua',
    checked: false
}, {
    id: 'power',
    displayName: 'Contadores electricidad',
    checked: false
}, {
    id: 'smartTv',
    displayName: 'Smart TV',
    checked: false
}];
