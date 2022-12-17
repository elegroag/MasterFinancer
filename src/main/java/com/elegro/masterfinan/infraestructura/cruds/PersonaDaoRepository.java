package com.elegro.masterfinan.infraestructura.cruds;

import com.elegro.masterfinan.infraestructura.dao.DaoRecord;
import com.elegro.masterfinan.infraestructura.dao.DaoRecordLong;
import com.elegro.masterfinan.infraestructura.entity.Persona;

public interface PersonaDaoRepository extends DaoRecord<Persona>, DaoRecordLong<Persona, Long> {
}
