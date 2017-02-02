package clases;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import clases.HibernateUtil;
import clases.Depart;

public class DepartamentoDao {

	private static SessionFactory sessionFactory;
	Session session = null;
	
	public boolean Existe(int id){

		Transaction tx;
		boolean existe = false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("from Depart where DEPT_NO = '"+id+"' ");
			List<Depart> personas = query.getResultList();
			existe = true;
			if(personas.isEmpty()){
				existe = false;
			}else{
				existe = true;
			}
		
			tx.commit();
		}catch(Exception e){
			
		}finally{
			session.close();
		}
		return existe;
		
		
	}

	public String Insertar(Depart d){
		
		Depart a = new Depart();
		Transaction tx;
		
		String salida = "";
		
		String nombre = d.getDnombre();
		String loc = d.getLoc();
		byte id = (byte)d.getDeptNo();
		
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
		
		try{
			a.setDnombre(nombre);
			a.setLoc(loc);
			a.setDeptNo(id);
			Depart dep=session.get(Depart.class, d.getDeptNo());
			if(dep==null){
				session.save(a);
			}else{
				return salida = "YA EXISTE ESE DEPARTAMENTO";	
			}
			tx.commit();
		
		}catch(Exception e){
			return salida = "NO SE HA PODIDO INSERTAR EL DEPARTAMENTO";	
		}
		
		return salida = "DEPARTAMENTO INSERTADO";
		
	}

	public String Eliminar(byte id){
			
		Depart dp = null;
		Transaction tx;
		String salida;
	
		if(Existe(id) == false ){
			return salida = "NO EXISTE ESE DEPARTAMENTO";
		}
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			dp = (Depart) session.get(Depart.class, id);
			session.delete(dp);
			tx.commit();
				
		}catch(Exception e){
			return salida = "FALLO AL BORRAR";
		}finally{
			session.close();
		}
		return salida = "DEPARTAMENTO BORRADO";
	}

	public String buscarDepart(int id){
		
		String empleado = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			
			Query query = session.createQuery("from Emple where DEPT_NO = "+id);
			List<Emple> personas = query.getResultList();
			
			Iterator<Emple> iter = personas.iterator();
			
			if(Existe(id) == false ){
				return empleado = "NO EXISTE ESE DEPARTAMENTO";
			}
			
			if(personas.isEmpty()){
				return empleado = "ESE DEPARTAMENTO NO TIENE EMPLEADOS";
			}
					
				while (iter.hasNext()){
				
					Emple dpt = iter.next();
					empleado +=  "OFICIO: " + dpt.getOficio() + "     Nombre: " + dpt.getApellido()+"\n";
			}
		
		}catch(Exception e){
			
		}finally{
			
			session.close();
		}
		return empleado;
		
	}

	public String Actualizar(byte id , String nombre , String loc){
		
		Depart dp = null;
		Transaction tx;
		String salida = "";
		
		if(Existe(id) == false ){
			return salida = "NO EXISTE ESE DEPARTAMENTO";
		}
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			dp = (Depart) session.get(Depart.class, id);
			
			dp.setDnombre(nombre);
			dp.setLoc(loc);
			session.update(dp);
			
			tx.commit();
		}catch(Exception e){
			return salida = "FALLO AL ACTUALIZAR";
		}finally{
			session.close();
		}
		return salida = "DEPARTAMENTO ACTUALIZADO";
	}

	public List Consultar(int id){
		
		Depart dp = null;
		Transaction tx;
		List<Depart> depart;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("from Depart where DEPT_NO = '"+id+"' ");
			depart = query.getResultList();
			
			tx.commit();
			
		}finally{
			session.close();
		}
		return depart;
	}
}
