package com.maveware.notariadigital.service.impl;

import com.maveware.notariadigital.dao.RegistroUsuarioDAO;
import com.maveware.notariadigital.dao.impl.RegistroUsuarioDAOImpl;
import com.maveware.notariadigital.service.RegistroUsuarioService;

/**
 * Created by GOAM on 22/03/2017.
 */

public class RegistroUsuarioServiceImpl implements RegistroUsuarioService {

    @Override
    public boolean registrarUsuario(String email, String password) {
        RegistroUsuarioDAO registrar = new RegistroUsuarioDAOImpl();

        return registrar.registroUsuario(email, password);
    }
}
