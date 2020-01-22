package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Errors_ {

    @SerializedName("Dto.Email")
    @Expose
    private List<String> dtoEmail = null;
    @SerializedName("Dto.Password")
    @Expose
    private List<String> dtoPassword = null;

    public List<String> getDtoEmail() {
        return dtoEmail;
    }

    public void setDtoEmail(List<String> dtoEmail) {
        this.dtoEmail = dtoEmail;
    }

    public List<String> getDtoPassword() {
        return dtoPassword;
    }

    public void setDtoPassword(List<String> dtoPassword) {
        this.dtoPassword = dtoPassword;
    }

}
