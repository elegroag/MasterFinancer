package com.elegro.masterfinan.infraestructura.cruds;

import com.elegro.masterfinan.infraestructura.dao.DaoRecord;
import com.elegro.masterfinan.infraestructura.dao.DaoRecordLong;
import com.elegro.masterfinan.infraestructura.entity.Usuario;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.util.Optional;

public interface UsuarioDaoRepository extends DaoRecord<Usuario>, DaoRecordLong<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);

}
