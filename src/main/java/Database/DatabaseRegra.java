package Database;

import net.bytebuddy.dynamic.Transformer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.*;

import java.util.List;

public class DatabaseRegra {
    private Configuration cfg = new Configuration()
            .addAnnotatedClass(RegraDb.class)
            .configure();
    private SessionFactory sessionFactory;
    private Session session;


    public RegraDb persist(RegraDb f){
        try{
            this.sessionFactory = cfg.buildSessionFactory();
            this.session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(f);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }

    public boolean delete(int id){
        try{
            this.sessionFactory = cfg.buildSessionFactory();
            this.session = sessionFactory.openSession();
            session.beginTransaction();
            RegraDb s = session.find(RegraDb.class, id);
            session.remove(s);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public RegraDb getById(int id){
        try{
            this.sessionFactory = cfg.buildSessionFactory();
            this.session = sessionFactory.openSession();
            session.beginTransaction();
            RegraDb s = session.find(RegraDb.class, id);
            session.getTransaction().commit();
            session.close();
            return s;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<RegraDb> getAll(){
        try{
            this.sessionFactory = cfg.buildSessionFactory();
            this.session = sessionFactory.openSession();
            session.beginTransaction();
            List<RegraDb> regrasList = session.createCriteria(RegraDb.class).list();
            session.getTransaction().commit();
            session.close();
            return regrasList;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void clear(){
        try{
            this.sessionFactory = cfg.buildSessionFactory();
            this.session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM regra").executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
