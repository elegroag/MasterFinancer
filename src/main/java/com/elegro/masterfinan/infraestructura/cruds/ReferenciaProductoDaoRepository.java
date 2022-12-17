package com.elegro.masterfinan.infraestructura.cruds;

import com.elegro.masterfinan.infraestructura.dao.DaoRecord;
import com.elegro.masterfinan.infraestructura.dao.DaoRecordLong;
import com.elegro.masterfinan.infraestructura.entity.ReferenciaProducto;
import com.elegro.masterfinan.infraestructura.entity.Transaccion;

public interface ReferenciaProductoDaoRepository extends DaoRecord<ReferenciaProducto>, DaoRecordLong<ReferenciaProducto, Long> {
}
