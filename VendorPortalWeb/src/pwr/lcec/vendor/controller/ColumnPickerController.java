package pwr.lcec.vendor.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIColumn;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.component.columntoggler.ColumnToggler;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.ToggleEvent;
import org.primefaces.util.ComponentTraversalUtils;

import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.interfaces.ColumnPickerServiceRemote;
import pwr.lcec.vendorportal.sec.entity.StakingSearchCol;

public class ColumnPickerController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ColumnPickerServiceRemote cps;
	
	private StakingSearchCol stakingSearchCol;
	
	ControllerUtil util = new ControllerUtil();
	
	/*@PostConstruct
	void init() {
		try {
			stakingSearchCol = cps.getStakingColByUser(util.getCurrentUser());
		} catch (ProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public void updateStakingSearchCol(ToggleEvent event) {

		/*if (event.getData() == null || !(event.getData() instanceof Integer) || !(event.getComponent() instanceof ColumnToggler)) {
			return;
		}
		
		ColumnToggler toggler = ((ColumnToggler) event.getComponent());
		UIComponent component = ComponentTraversalUtils.firstWithId(toggler.getDatasource(), FacesContext.getCurrentInstance().getViewRoot());
		if(component == null || !(component instanceof DataTable)){
			return;
		}
		
		DataTable table = (DataTable) component;
		String columnFullId = (Integer)event.getData() >= table.getColumns().size() ? null : table.getColumns().get((Integer)event.getData()).getAriaHeaderText();
		if(columnFullId != null){
			String columnId = columnFullId.replace(table.getClientId(), "").replace(":", "");
			System.out.println("Column Full ID: "+columnFullId);
			System.out.println("Column ID: "+columnId);
			
		}*/
		String tblName = event.getComponent().getClientId();
		Integer semiColIndex = tblName.indexOf(":");
		String colName = event.getComponent().getClientId();
		String tblId = tblName.substring(0,semiColIndex);
		String columnIndex = event.getData().toString();
		
		System.out.println("Table Name: " + tblId);
		System.out.println("Column Index: " + columnIndex);
		System.out.println("Visibility: " + event.getVisibility());
	}

	public StakingSearchCol getStakingSearchCol() {
		return stakingSearchCol;
	}

	public void setStakingSearchCol(StakingSearchCol stakingSearchCol) {
		this.stakingSearchCol = stakingSearchCol;
	}

}
