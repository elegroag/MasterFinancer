package com.elegro.masterfinan.infraestructura.cruds;


import com.elegro.masterfinan.infraestructura.dao.DaoRecord;
import com.elegro.masterfinan.infraestructura.dao.DaoRecordInteger;
import com.elegro.masterfinan.infraestructura.entity.Compra;

public interface CompraDaoRepository  extends DaoRecord<Compra>, DaoRecordInteger<Compra, Integer> {
}
