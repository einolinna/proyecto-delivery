import { RubroArticulo } from './RubroArticulo';

export class ArticuloInsumo {
  id: number;
  denominacion: string;
  precioCompra: number;
  precioVenta: number;
  stockActual: number;
  stockMinimo: number;
  unidadMedida: string;
  esInsumo: boolean;
  rubroArticulo: RubroArticulo;
}
