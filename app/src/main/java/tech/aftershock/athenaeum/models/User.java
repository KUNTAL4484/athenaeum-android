package tech.aftershock.athenaeum.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user_id")
    private String userId;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("class_roll")
    private String classRoll;

    @SerializedName("department_id")
    private String departmentId;

    @SerializedName("semester")
    private String semester;

    @SerializedName("department_name")
    private String departmentName;

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getClassRoll() {
        return classRoll;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getSemester() {
        return semester;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
