package pwr.lcec.vendor.web.convert;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import java.util.List;
import java.util.Objects;

/**
 * Generic converter for primefaces picklist. Without it, the setter of
 * DualListModel<Object> sets its DualListModel<Object> source and target lists
 * as lists with type String (DualListModel<String>) instead of lists with the
 * correct Object values.
 *
 * NOTE: The converter is relying on object.hashCode() - mind the implications
 * of a hash collision, you can override the corresponding object's hashCode()
 * to account for that.
 *
 * @author dahoc
 */
@FacesConverter(value = "pfPickListConverter")
public class PicklistGenericConverter implements Converter {
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object entity) {
		if (entity == null)
			return "";
		return String.valueOf(entity.hashCode());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String uuid) {
		Object ret = null;
		if (uuid == null || uuid.equals(""))
			return null;

		if (component instanceof PickList) {
			final Object dualList = ((PickList) component).getValue();
			final DualListModel dl = (DualListModel) dualList;
			ret = retrieveObject(dl.getSource(), uuid);
			if (ret == null)
				ret = retrieveObject(dl.getTarget(), uuid);
		}

		return ret;
	}

	/**
	 * Function retrieves the object by its hashCode. Because this has to be
	 * generic, typing is raw - and therefore disable IDE warning.
	 *
	 * @param objects
	 *            list of arbitrary objects
	 * @param uuid
	 *            hashCode of the object to retrieve
	 * @return correct object with corresponding hashCode or null, if none found
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object retrieveObject(final List objects, final String uuid) {
		return objects.stream().filter(Objects::nonNull).filter(obj -> uuid.equals(String.valueOf(obj.hashCode())))
				.findFirst().orElse(null);
	}
}
