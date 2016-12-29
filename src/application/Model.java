package application;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import bean.Parola;
import db.Dao;

public class Model {
	
	private Dao dao = new Dao();
	private UndirectedGraph<String, DefaultEdge> grafo ;
	
	public boolean isPres(String nome){
		return dao.isPresente(nome);
	}
	
	public List<Parola> getParole(int lung){
		List<Parola> pa = dao.getParole(lung);
		return pa;
	}
	
	public int getCon(int lung){
		int conta = dao.getConta(lung);
		return conta;
	}
	
	public boolean isCollegata(String p1 , String p2){
		if(p1.equals(p2)){
			return false;
		}
		if(p1!= null && p2!= null){
			if(isPres(p1) && isPres(p2)){
				int lun1 = p1.length();
				int lun2 = p2.length();
				if(lun1==lun2){
					String ultime = p1.substring(p1.length()-2, p1.length());
					String prime = p2.substring(0, 2);
					if(ultime.equals(prime)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public UndirectedGraph<String, DefaultEdge> buildGraph(int lung){
		grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		List<Parola> parole = getParole(lung);
		for(Parola p : parole){
			grafo.addVertex(p.getNome());
		}
		for(String n1 : grafo.vertexSet()){
			for(String n2 : grafo.vertexSet()){
				if(isCollegata(n1, n2)){
					grafo.addEdge(n1, n2);
				}
			}
		}
		System.out.println(grafo.toString());
		return grafo;
	}
	
	public int numV(UndirectedGraph<String, DefaultEdge> grafo){
		int numV = grafo.vertexSet().size();
		return numV;
	}
	
	public int numA(UndirectedGraph<String, DefaultEdge>grafo){
		int numA = grafo.edgeSet().size();
		return numA;
	}
	

	
	public List<String> calcoloGradiVertici(UndirectedGraph<String, DefaultEdge>grafo){
		List<String> nonragg = new LinkedList<String>();
		for(String p : grafo.vertexSet()){
			 grafo.degreeOf(p);
			 if(grafo.degreeOf(p)==0){
				nonragg.add(p);
			}	
		}
		return nonragg;
	}

	public List<String> getCammino(String s1, String s2) {
		if(s1!= null && s2!= null){
		if(!s1.equals(s2)){
		DijkstraShortestPath<String, DefaultEdge> cammino = new DijkstraShortestPath<String, DefaultEdge>(grafo, s1, s2);
		GraphPath<String, DefaultEdge> path = cammino.getPath();
		if(path==null){
			return null;
		}
		return Graphs.getPathVertexList(path);	
		}
		}
		return null;
	}

}
