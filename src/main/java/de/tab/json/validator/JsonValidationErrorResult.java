package de.tab.json.validator;

import com.networknt.schema.ValidationMessage;

import java.util.ArrayList;
import java.util.List;

public class JsonValidationErrorResult {
    private Integer errorCode = 0;
    private String description;

    private Exception exception;

    private List<ValidationMessage> errorList;

    public JsonValidationErrorResult() {
        errorList = new ArrayList<ValidationMessage>();
    }

    public static JsonValidationErrorResult getInstance() {
        return new JsonValidationErrorResult();
    }

    public Boolean success() {
        return  errorCode == 0;
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Exception getException() {
        return this.exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public void addValidationError(ValidationMessage msg) {
        errorList.add(msg);
    }

    public void removeValidationError(ValidationMessage msg) {
        if (errorList.contains(msg))
            errorList.remove(msg);
    }

    public List<ValidationMessage> getErrorList() {
        return this.errorList;
    }

    public void setErrorList(List<ValidationMessage> errorList) {
        this.errorList = errorList;
    }
}

