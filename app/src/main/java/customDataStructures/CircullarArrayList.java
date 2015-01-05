package customDataStructures;
import java.util.ArrayList;
/**
 * Created by anubhav.kaushik on 9/15/2014.
 */


public class CircullarArrayList <E> extends ArrayList<E>
{
    private static final long serialVersionUID = 1L;

  public E getTheNextElement(E incomingElement)
  {   E element;
      int incomingIndex = this.indexOf(incomingElement);
      if(incomingIndex == this.size()-1 )
      {    element = this.get(0);
           return element;
      }
      else
      {        element = this.get(incomingIndex+1);
           return element;
      }
  }

}