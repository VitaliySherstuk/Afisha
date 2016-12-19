package by.htp.logic;

import java.util.Date;
import by.htp.entity.Afisha;

public interface AfishaVisitor {

	 void loadAfisha(Afisha afisha);
	 void loadAfisha(Afisha afisha, Date date);
	 void loadAfisha(Afisha afisha, Date dateStart, Date dateEnd);
}
