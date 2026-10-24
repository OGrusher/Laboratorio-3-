package edu.ufg.aulas.dao;

import edu.ufg.aulas.entidades.Usuario;
import edu.ufg.aulas.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Optional;

public class UsuarioDAO {

    public Optional<Usuario> buscarPorEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Usuario u where u.email = :email", Usuario.class)
                    .setParameter("email", email)
                    .uniqueResultOptional();
        }
    }
}
