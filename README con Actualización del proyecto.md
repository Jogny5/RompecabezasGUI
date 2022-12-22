Informe Proyecto Rompecabezas
Grupo 17

Diego Gutiérrez Mendoza
Jorge Fernández Hermosilla


Actualización Proyecto Rompecabezas:

Se descartó la idea de utilizar los poligonos gráficamente y se optó por usar BufferedImage los cuales utlizaban los polígonos formados para hacer que las partes que estos cubrían en el label ahora cambien los píxeles de los BufferedImage y los deje transparentes para que así se vean todas las piezas mientras estas se mueven por el jugador.

El problema de que solo se podía rotar los labeles y no los poligonos, dejando inconclusa la pieza ha sido resulto gracias a este nuevo método de hacer las piezas ya que solo depende del ícono que se les entregue. Ahora a partir de los íconos se crean RotatedIcons, los cuales tienen propiedades que nos da la facilidad de rotar otros iconos, lo cual facilita rotar las piezas ya que tan solo se le integra el icono rotado al label. Para que el label no quedara del tamaño de la imagen original con todos sus pixeles transparentes se agregó un método que recorta estos pixeles y deja la imagen lo más pequeña posible, después el label toma el tamaño de la nueva imagen y tendrá un área bastante aproximada a lo que es la imagen que representa la pieza.

Se integró el modo de juego el cual cambia aleatoriamente tanto la posición de las piezas como su rotación, y cuando estas esten en un rango próximo a su lugar en el rompecabezas, estas se encajaran y no se podrán mover, hasta que se llene el rompecabezas y termine el juego. Además el modo editor se puede reiniciar dándole click al botón en cualquier momento, y el modo de juego se puede volver a jugar si se da click a su botón cuando se complete el rompecabezas o después de hacer la edición deseada. Para hacer las piezas cuando ya están encajadas en el modo de juego se utilizó los polígonos que iban acoplados a cada pieza y cuando estén en proximidad de un área encontrada en el punto de de intersección y se suelte el mouse, aparecerá un label con la imagen correspondiente y con el cual no se puede interactuar.




