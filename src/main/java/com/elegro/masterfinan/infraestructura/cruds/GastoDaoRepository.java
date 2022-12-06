package com.elegro.masterfinan.infraestructura.cruds;

import com.elegro.masterfinan.infraestructura.dao.DaoRecord;
import com.elegro.masterfinan.infraestructura.dao.DaoRecordLong;
import com.elegro.masterfinan.infraestructura.entity.Gasto;

public interface GastoDaoRepository extends DaoRecord<Gasto>, DaoRecordLong<Gasto, Long> {
}
