package rompecabezas_gui;
import java.awt.*;
/**
 * Clase para hacer una linea hecha de puntos en un poligono
 * @author Jorge
 * @author Diego
 */
public class RellenaConPuntos {
    /**
     * Constructor de la clase RellenaConPuntos
     */
    public RellenaConPuntos(){
    }
    
    /**
     * Crea una linea a partir de los 2 puntos
     * @param a Punto de inicio de la linea 
     * @param b Punto de termino de la linea
     * @param p Plygon donde se almacenan todos los puntos en linea recta entre los dos puntos
     */
        public synchronized static void nuevaLinea(Point a, Point b, Polygon p){
            nuevaLinea(a.x,a.y,b.x,b.y,p);
        }
        /**
         * Crea una linea a partir de la posicion de cada coordenada de los puntos
         * @param x1 Posicion x del inicio de la linea
         * @param y1 Posicion y del inicio de la linea
         * @param x2 Posicion x del termino de la linea
         * @param y2 Posicion y del termino de la linea
         * @param p Poligono para hacer la linea
         */
        public synchronized static void nuevaLinea(int x1,int y1,int x2,int y2, Polygon p){
           int npy=0;
           if(x1==x2&&y1==y2)return;
           if(y1>y2)npy=y1-y2-1;else npy=y2-y1-1;            
            int npx=0;
           if(x1>x2)npx=x1-x2-1; else npx=x2-x1-1;
           int np;
           if(npy>npx)np=npy;else np=npx;
           if(np<1)return;
           if(npy>npx){
               if(npy>0){creaLineaY(x1,y1,x2,y2,np,p);return;}
               else return;
           } else {
               if(npx>0){creaLineaX(x1,y1,x2,y2,np,p);return;}
               else return;
           }
        }
        /**
         * Crea una linea desde la coordenada x
         * @param nx1 Coordenada x del inicio de la linea
         * @param ny1 Coordenada y del inicio de la linea
         * @param nx2 Coordenada x del termino de la linea
         * @param ny2 Coordenada y del termino de la linea
         * @param np Cantidad de puntos que van a componer la linea
         * @param p Poligono que va a estar hecho de lineas
         */
        private static void creaLineaX(int nx1,int ny1,int nx2,int ny2, int np, Polygon p){
	   double m = (((double)ny2-(double)ny1))/(((double)nx2-(double)nx1));
	   String borrar = Double.toString(m);
	   if(borrar.endsWith("-Infinity")||borrar.endsWith("Infinity")){
		for (int i = 1; i< np+1;i++){
                    if(nx1<nx2)p.addPoint(nx1,ny1+i);
                    else p.addPoint(nx1,ny1-i);
                }
           } else {
		double b = (double)ny1-m*(double)nx1;
		for (int i = 1; i < np+1;i++){
                   int yi;
		   if(nx1<nx2){
                       yi= (int)((double)(nx1+i)*m+(double)b);
                       p.addPoint(nx1+i,yi);
                   } else {
                       yi= (int)((double)(nx1-i)*m+(double)b);
                       p.addPoint(nx1-i,yi);
                   }
	       }
           }
       } 
        /**
         * Crea una linea desde la coordenada y
         * @param nx1 Coordenada x del inicio de la linea
         * @param ny1 Coordenada y del inicio de la linea
         * @param nx2 Coordenada x del termino de la linea
         * @param ny2 Coordenada y del termino de la linea
         * @param np Cantidad de puntos que van a componer la linea
         * @param p Poligono que va a estar hecho de lineas
         */
       private static void creaLineaY(int nx1,int ny1,int nx2,int ny2, int np, Polygon p){
	   double m = (((double)ny2-(double)ny1))/(((double)nx2-(double)nx1));
	   String borrar = Double.toString(m);
	   if(borrar.endsWith("-Infinity")||borrar.endsWith("Infinity")){
		for (int i = 1; i<np+1;i++){
                    if(ny1<ny2)p.addPoint(nx1,ny1+i);
                    else p.addPoint(nx1,ny1-i);
                }
	   } else {
		double b = (double)ny1-m*(double)nx1;
		for (int i = 1; i <np+1;i++){
                    int xi;
                 if(ny1<ny2){                                                 
                     xi= (int)(((double)(ny1+i)-(double)b)/m);
                     p.addPoint(xi,ny1+i);
                 } else {
                     xi= (int)(((double)(ny1-i)-(double)b)/m);
                     p.addPoint(xi,ny1-i);
                 }
	        }
           }
       } 
}


