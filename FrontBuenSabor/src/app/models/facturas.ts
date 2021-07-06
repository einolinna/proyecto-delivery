import { detalleFactura } from "./detalleFactura";


export class facturas {
  fecha: Date;
  numero: number;
  montoDescuento: number;
  formaPago: string;
  nroTarjeta: string;
  totalVenta: number;
  totalCosto: number;
  detalleFactura:detalleFactura;
}
