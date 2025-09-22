// 代码生成时间: 2025-09-22 12:47:47
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * 表单数据验证器，用于验证表单输入是否符合要求。
 */
@FacesValidator(value = "formValidator")
public class FormValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        // 检查value是否为null
        if (value == null || value.toString().trim().isEmpty()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value cannot be null or empty.", null));
        }

        // 假设我们需要验证字符串长度是否在5到10之间
        if (!(value instanceof String)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value must be a string.", null));
        }

        String stringValue = value.toString();
        if (stringValue.length() < 5 || stringValue.length() > 10) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value must be between 5 and 10 characters long.", null));
        }
    }
}
