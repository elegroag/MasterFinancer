package com.elegro.masterfinan.infraestructura.cruds;

import com.elegro.masterfinan.infraestructura.dao.DaoRecord;
import com.elegro.masterfinan.infraestructura.dao.DaoRecordInteger;
import com.elegro.masterfinan.infraestructura.entity.Transaccion;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.util.List;

public interface TransaccionDaoRepository extends DaoRecord<Transaccion>, DaoRecordInteger<Transaccion, Integer> {
    List<Transaccion> findByIngresoCategoria(Long idCatgeoria) throws DaoException;
}
