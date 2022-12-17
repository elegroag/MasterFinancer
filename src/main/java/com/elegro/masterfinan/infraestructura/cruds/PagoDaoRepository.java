package com.elegro.masterfinan.infraestructura.cruds;

import com.elegro.masterfinan.infraestructura.dao.DaoRecord;
import com.elegro.masterfinan.infraestructura.dao.DaoRecordLong;
import com.elegro.masterfinan.infraestructura.entity.Pago;

public interface PagoDaoRepository extends DaoRecord<Pago>, DaoRecordLong<Pago, Long> {
}
