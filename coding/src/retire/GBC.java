/**
 * 
 */
package retire;

import java.awt.GridBagConstraints;

/**
 * @author bowen
 *
 */
public class GBC extends GridBagConstraints {
	public GBC(int gridx,int gridy){
		this.gridx=gridx;
		this.gridy=gridy;
	}
	
	public GBC(int gridx,int gridy,int gridwidth,int gridheight){
		this.gridx=gridx;
		this.gridy=gridy;
		this.gridwidth=gridwidth;
		this.gridheight=gridheight;
	}
	
	public GBC setAnchor(int anchor){
		this.anchor=anchor;
		return this;
	}
	
	public GBC setFill(int fill){
		this.fill=fill;
		return this;
	}
	
	public GBC setWeight(int weightx,int weighty){
		this.weightx=weightx;
		this.weighty=weighty;
		return this;
	}
}
