import { ArticuloMfactDetalle } from './ArticuloMfactDetalle';

export class ArticuloMfact {
  id: number;
  tiempoEstimadoCoccion: number;
  denominacion: string;
  precioVenta: number;
  imagen: string;
  rubroGeneral: {
    denominacion: string;
  };
  articulosMfactDetalle: ArticuloMfactDetalle[];
}
