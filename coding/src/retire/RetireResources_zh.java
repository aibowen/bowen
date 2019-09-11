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
public class RetireResources_zh extends ListResourceBundle {
	
	private static final Object[][] contents={
			{"colorPre",Color.red},
			{"colorGain",Color.blue},
			{"colorLoss",Color.yellow}
	};
	/* (non-Javadoc)
	 * @see java.util.ListResourceBundle#getContents()
	 */
	@Override
	protected Object[][] getContents() {
		return contents;
	}

}
