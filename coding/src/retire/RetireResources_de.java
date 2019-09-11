/**
 * 
 */
package retire;

import java.awt.Color;
import java.util.ListResourceBundle;

/**
 * @author bowen
 *
 */
public class RetireResources_de extends ListResourceBundle {
	
	private static final Object[][] contents={
			{"colorPre",Color.yellow},
			{"colorGain",Color.black},
			{"colorLoss",Color.red}
	};
	/* (non-Javadoc)
	 * @see java.util.ListResourceBundle#getContents()
	 */
	@Override
	protected Object[][] getContents() {
		return contents;
	}

}
