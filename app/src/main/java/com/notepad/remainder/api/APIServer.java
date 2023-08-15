package com.notepad.remainder.api;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.jacksonandroidnetworking.JacksonParserFactory;
import com.techmates.mymandirmyseva.Global.GlobalApplication;

import org.json.JSONObject;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class APIServer {

    Context context;
    Properties properties;
    String subUrl = "";
    String mainUrl = "";

    public APIServer(Context context) {
        this.context = context;
        properties = new LoadAssetProperties().loadRESTApiFile(context.getResources(), "rest", context);
        mainUrl = properties.getProperty("MainUrl");
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
    }

    public void verifyMobile(final APIResponse listener, String mobile) {
        subUrl = properties.getProperty("mobile");

        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("mobile", mobile)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);

                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());

                    }
                });
    }

    public void categoryList(final APIResponse listener, String templeId) {
        subUrl = properties.getProperty("categoryList");
        AndroidNetworking.get(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addQueryParameter("templeId", templeId)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void departmentList(final APIResponse listener, String templeId) {
        subUrl = properties.getProperty("departmentList");
        AndroidNetworking.get(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addQueryParameter("templeId", templeId)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void addDepartment(final APIResponse listener, String departmentName, String templeId, String admin_id) {

        subUrl = properties.getProperty("addDepartment");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("name", departmentName)
                .addBodyParameter("templeId", templeId)
                .addBodyParameter("admin_id", admin_id)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void addManager(final APIResponse listener, String templeId, String departmentId, String first_name, String last_name, String email, String mobile, String address, String admin_id) {

        subUrl = properties.getProperty("addManager");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("temple_id", templeId)
                .addBodyParameter("departmentId", departmentId)
                .addBodyParameter("first_name", first_name)
                .addBodyParameter("last_name", last_name)
                .addBodyParameter("email", email)
                .addBodyParameter("mobile", mobile)
                .addBodyParameter("address", address)
                .addBodyParameter("admin_id", admin_id)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void editManager(final APIResponse listener, String managerId, String departmentId, String first_name, String last_name, String email, String mobile, String address, String admin_id) {

        subUrl = properties.getProperty("editManager");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("managerId", managerId)
                .addBodyParameter("departmentId", departmentId)
                .addBodyParameter("first_name", first_name)
                .addBodyParameter("last_name", last_name)
                .addBodyParameter("email", email)
                .addBodyParameter("mobile", mobile)
                .addBodyParameter("address", address)
                .addBodyParameter("admin_id", admin_id)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void managerList(final APIResponse listener, String templeId) {
        subUrl = properties.getProperty("managerList");
        AndroidNetworking.get(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addQueryParameter("templeId", templeId)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void addStaff(final APIResponse listener, String templeId, String categoryId, String first_name, String last_name, String email, String mobile, String address, String admin_id) {

        subUrl = properties.getProperty("addStaff");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("temple_id", templeId)
                .addBodyParameter("categoryId", categoryId)
                .addBodyParameter("first_name", first_name)
                .addBodyParameter("last_name", last_name)
                .addBodyParameter("email", email)
                .addBodyParameter("mobile", mobile)
                .addBodyParameter("address", address)
                .addBodyParameter("admin_id", admin_id)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void addEvent(final APIResponse listener, String title, String date, String time, String location, String description, String templeId, String admin_id, Map<String, String> manager, Map<String, String> staff) {

        subUrl = properties.getProperty("addEvent");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("title", title)
                .addBodyParameter("date", date)
                .addBodyParameter("time", time)
                .addBodyParameter("location", location)
                .addBodyParameter("description", description)
                .addBodyParameter("templeId", templeId)
                .addBodyParameter("admin_id", admin_id)
                .addBodyParameter(staff)
                .addBodyParameter(manager)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void addEventWithMedia(final APIResponse listener, String title, String date, String time, String location, String description, String templeId, String admin_id, Map<String, String> manager, Map<String, String> staff, String file) {

        subUrl = properties.getProperty("addEvent");
        AndroidNetworking.upload(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addMultipartParameter("title", title)
                .addMultipartParameter("date", date)
                .addMultipartParameter("time", time)
                .addMultipartParameter("location", location)
                .addMultipartParameter("description", description)
                .addMultipartParameter("templeId", templeId)
                .addMultipartParameter("admin_id", admin_id)
                .addMultipartFile("media", new File(file))
                .addMultipartParameter(staff)
                .addMultipartParameter(manager)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void editEvent(final APIResponse listener, String id, String title, String date, String time, String location, String description, String templeId, String admin_id, Map<String, String> manager, Map<String, String> staff) {
        subUrl = properties.getProperty("addEvent") + "/" + id;
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("title", title)
                .addBodyParameter("date", date)
                .addBodyParameter("time", time)
                .addBodyParameter("location", location)
                .addBodyParameter("description", description)
                .addBodyParameter("templeId", templeId)
                .addBodyParameter("admin_id", admin_id)
                .addBodyParameter(staff)
                .addBodyParameter(manager)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void editEventWithMedia(final APIResponse listener, String id, String title, String date, String time, String location, String description, String templeId, String admin_id, Map<String, String> manager, Map<String, String> staff, String file) {
        subUrl = properties.getProperty("addEvent") + "/" + id;
        AndroidNetworking.upload(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addMultipartParameter("title", title)
                .addMultipartParameter("date", date)
                .addMultipartParameter("time", time)
                .addMultipartParameter("location", location)
                .addMultipartParameter("description", description)
                .addMultipartFile("media", new File(file))
                .addMultipartParameter("templeId", templeId)
                .addMultipartParameter("admin_id", admin_id)
                .addMultipartParameter(staff)
                .addMultipartParameter(manager)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void editStaff(final APIResponse listener, String staffId, String categoryId, String first_name, String last_name, String email, String mobile, String address, String admin_id) {

        subUrl = properties.getProperty("editStaff");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("staffId", staffId)
                .addBodyParameter("categoryId", categoryId)
                .addBodyParameter("first_name", first_name)
                .addBodyParameter("last_name", last_name)
                .addBodyParameter("email", email)
                .addBodyParameter("mobile", mobile)
                .addBodyParameter("address", address)
                .addBodyParameter("admin_id", admin_id)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void staffList(final APIResponse listener, String templeId) {
        subUrl = properties.getProperty("staffList");
        AndroidNetworking.get(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addQueryParameter("templeId", templeId)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void addComplaint(final APIResponse listener, String templeId, String user_id, String category_id, String staff_id, String department_id, String title, String description, List<File> attachment) {

        subUrl = properties.getProperty("addComplaint");
        if (attachment.size() == 0) {
            AndroidNetworking.post(mainUrl + subUrl)
                    .addHeaders("Auth-Key", GlobalApplication.auth)
                    .addBodyParameter("temple_id", templeId)
                    .addBodyParameter("manager_id", user_id)
                    .addBodyParameter("category_id", category_id)
                    .addBodyParameter("staff_id", staff_id)
                    .addBodyParameter("department_id", department_id)
                    .addBodyParameter("title", title)
                    .addBodyParameter("description", description)
                    .addBodyParameter("image[]", "")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            listener.onSuccess(response);
                        }

                        @Override
                        public void onError(ANError anError) {
                            listener.onFailure(anError.getErrorBody());
                        }
                    });
        } else {
            AndroidNetworking.upload(mainUrl + subUrl)
                    .addHeaders("Auth-Key", GlobalApplication.auth)
                    .addMultipartParameter("temple_id", templeId)
                    .addMultipartParameter("manager_id", user_id)
                    .addMultipartParameter("category_id", category_id)
                    .addMultipartParameter("staff_id", staff_id)
                    .addMultipartParameter("department_id", department_id)
                    .addMultipartParameter("title", title)
                    .addMultipartParameter("description", description)
                    .addMultipartFileList("image[]", attachment)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            listener.onSuccess(response);
                        }

                        @Override
                        public void onError(ANError anError) {
                            listener.onFailure(anError.getErrorBody());
                        }
                    });
        }

    }

    public void editComplaint(final APIResponse listener, String templeId, String complaintId, String user_id, String category_id, String staff_id, String department_id, String title, String description, String reason, List<File> attachment) {

        subUrl = properties.getProperty("editComplaint");
        if (attachment.size() == 0) {
            AndroidNetworking.post(mainUrl + subUrl)
                    .addHeaders("Auth-Key", GlobalApplication.auth)
                    .addBodyParameter("templeId", templeId)
                    .addBodyParameter("complaintId", complaintId)
                    .addBodyParameter("manager_id", user_id)
                    .addBodyParameter("category_id", category_id)
                    .addBodyParameter("staff_id", staff_id)
                    .addBodyParameter("department_id", department_id)
                    .addBodyParameter("title", title)
                    .addBodyParameter("description", description)
                    .addBodyParameter("reason", reason)
                    .addBodyParameter("image[]", "")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            listener.onSuccess(response);
                        }

                        @Override
                        public void onError(ANError anError) {
                            listener.onFailure(anError.getErrorBody());
                        }
                    });
        } else {
            AndroidNetworking.upload(mainUrl + subUrl)
                    .addHeaders("Auth-Key", GlobalApplication.auth)
                    .addMultipartParameter("templeId", templeId)
                    .addMultipartParameter("complaintId", complaintId)
                    .addMultipartParameter("manager_id", user_id)
                    .addMultipartParameter("category_id", category_id)
                    .addMultipartParameter("staff_id", staff_id)
                    .addMultipartParameter("department_id", department_id)
                    .addMultipartParameter("title", title)
                    .addMultipartParameter("description", description)
                    .addMultipartParameter("reason", reason)
                    .addMultipartFileList("image[]", attachment)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            listener.onSuccess(response);
                        }

                        @Override
                        public void onError(ANError anError) {
                            listener.onFailure(anError.getErrorBody());
                        }
                    });
        }

    }

    public void addComplaintManager(final APIResponse listener, String templeId, String user_id, String category_id, String department_id, String title, String description, List<File> attachment) {

        subUrl = properties.getProperty("addComplaint");
        if (attachment.size() == 0) {
            AndroidNetworking.post(mainUrl + subUrl)
                    .addHeaders("Auth-Key", GlobalApplication.auth)
                    .addBodyParameter("temple_id", templeId)
                    .addBodyParameter("manager_id", user_id)
                    .addBodyParameter("category_id", category_id)
                    .addBodyParameter("department_id", department_id)
                    .addBodyParameter("title", title)
                    .addBodyParameter("description", description)
                    .addBodyParameter("image[]", "")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            listener.onSuccess(response);
                        }

                        @Override
                        public void onError(ANError anError) {
                            listener.onFailure(anError.getErrorBody());
                        }
                    });
        } else {
            AndroidNetworking.upload(mainUrl + subUrl)
                    .addHeaders("Auth-Key", GlobalApplication.auth)
                    .addMultipartParameter("temple_id", templeId)
                    .addMultipartParameter("manager_id", user_id)
                    .addMultipartParameter("category_id", category_id)
                    .addMultipartParameter("department_id", department_id)
                    .addMultipartParameter("title", title)
                    .addMultipartParameter("description", description)
                    .addMultipartFileList("image[]", attachment)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            listener.onSuccess(response);
                        }

                        @Override
                        public void onError(ANError anError) {
                            listener.onFailure(anError.getErrorBody());
                        }
                    });
        }

    }

    public void editComplaintManager(final APIResponse listener, String templeId, String complaintId, String user_id, String category_id, String title, String description, String reason, List<File> attachment) {

        subUrl = properties.getProperty("editComplaint");
        if (attachment.size() == 0) {
            AndroidNetworking.post(mainUrl + subUrl)
                    .addHeaders("Auth-Key", GlobalApplication.auth)
                    .addBodyParameter("templeId", templeId)
                    .addBodyParameter("complaintId", complaintId)
                    .addBodyParameter("manager_id", user_id)
                    .addBodyParameter("category_id", category_id)
                    .addBodyParameter("title", title)
                    .addBodyParameter("description", description)
                    .addBodyParameter("reason", reason)
                    .addBodyParameter("image[]", "")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            listener.onSuccess(response);
                        }

                        @Override
                        public void onError(ANError anError) {
                            listener.onFailure(anError.getErrorBody());
                        }
                    });
        } else {
            AndroidNetworking.upload(mainUrl + subUrl)
                    .addHeaders("Auth-Key", GlobalApplication.auth)
                    .addMultipartParameter("templeId", templeId)
                    .addMultipartParameter("complaintId", complaintId)
                    .addMultipartParameter("manager_id", user_id)
                    .addMultipartParameter("category_id", category_id)
                    .addMultipartParameter("title", title)
                    .addMultipartParameter("description", description)
                    .addMultipartParameter("reason", reason)
                    .addMultipartFileList("image[]", attachment)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            listener.onSuccess(response);
                        }

                        @Override
                        public void onError(ANError anError) {
                            listener.onFailure(anError.getErrorBody());
                        }
                    });
        }

    }

    public void complaintListAdmin(final APIResponse listener, String templeId, String complaintTypeId, String segment) {
        subUrl = properties.getProperty("complaintList");
        AndroidNetworking.get(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addQueryParameter("templeId", templeId)
                .addQueryParameter("status", complaintTypeId)
                .addQueryParameter("segment", segment)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void complaintListManager(final APIResponse listener, String templeId, String departmentId, String complaintTypeId, String segment) {
        subUrl = properties.getProperty("complaintList");
        AndroidNetworking.get(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addQueryParameter("templeId", templeId)
                .addQueryParameter("departmentId", departmentId)
                .addQueryParameter("status", complaintTypeId)
                .addQueryParameter("segment", segment)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void assignComplaint(final APIResponse listener, String complaintId, String templeId, String staff_id, String description, String categoryId) {

        subUrl = properties.getProperty("assignComplaint");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("complaintId", complaintId)
                .addBodyParameter("templeId", templeId)
                .addBodyParameter("userId", staff_id)
                .addBodyParameter("categoryId", categoryId)
                .addBodyParameter("description", description)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void reopenComplaint(final APIResponse listener, String complaintId, String templeId, String description) {
        subUrl = properties.getProperty("reopenComplaint");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("complaintId", complaintId)
                .addBodyParameter("templeId", templeId)
                .addBodyParameter("description", description)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void dashboard(final APIResponse listener, String templeId) {
        subUrl = properties.getProperty("dashboard");
        AndroidNetworking.get(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addQueryParameter("templeId", templeId)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void staffDashboard(final APIResponse listener, String templeId, String staffId) {
        subUrl = properties.getProperty("staffComplaintDashboard");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("templeId", templeId)
                .addBodyParameter("staffId", staffId)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void staffComplaintList(final APIResponse listener, String templeId, String staffId, String complaintTypeId, String segment) {
        subUrl = properties.getProperty("staffComplaintList");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("templeId", templeId)
                .addBodyParameter("staffId", staffId)
                .addBodyParameter("status", complaintTypeId)
                .addQueryParameter("segment", segment)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void changeComplaintStatus(final APIResponse listener, String complaintId, String status, String templeId, String userId) {
        subUrl = properties.getProperty("changeComplaintStatus");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("complaintId", complaintId)
                .addBodyParameter("templeId", templeId)
                .addBodyParameter("userId", userId)
                .addBodyParameter("status", status)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void changeProfilePhoto(final APIResponse listener, String uniqid, String images) {
        subUrl = properties.getProperty("changeProfilePhoto");
        AndroidNetworking.upload(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addMultipartParameter("uniqid", uniqid)
                .addMultipartFile("images", new File(images))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void deviceInfo(final APIResponse listener, String userId, String firebase_id, String device_id, String device_token, String device_type, String app_version, String device_model, String os_version) {
        subUrl = properties.getProperty("deviceInfo");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("userId", userId)
                .addBodyParameter("firebase_id", firebase_id)
                .addBodyParameter("device_id", device_id)
                .addBodyParameter("device_token", device_token)
                .addBodyParameter("device_type", device_type)
                .addBodyParameter("app_version", app_version)
                .addBodyParameter("device_model", device_model)
                .addBodyParameter("os_version", os_version)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void search(final APIResponse listener, String searchtxt, String templeId) {
        subUrl = properties.getProperty("search");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("searchtxt", searchtxt)
                .addBodyParameter("templeId", templeId)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void searchManager(final APIResponse listener, String searchtxt, String templeId, String departmentId) {
        subUrl = properties.getProperty("search");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("searchtxt", searchtxt)
                .addBodyParameter("templeId", templeId)
                .addBodyParameter("departmentId", departmentId)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void searchStaff(final APIResponse listener, String searchtxt, String templeId, String staffId) {
        subUrl = properties.getProperty("search");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("searchtxt", searchtxt)
                .addBodyParameter("templeId", templeId)
                .addBodyParameter("staffId", staffId)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void deleteImage(final APIResponse listener, String imageKey, String complaintId, String templeId) {
        subUrl = properties.getProperty("deleteImage");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("imageKey", imageKey)
                .addBodyParameter("complaintId", complaintId)
                .addBodyParameter("templeId", templeId)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void complaintDetails(final APIResponse listener, String templeId, String complaintId) {
        subUrl = properties.getProperty("complaintDetails") + complaintId;
        AndroidNetworking.get(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addQueryParameter("templeId", templeId)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void logOut(final APIResponse listener, String deviceId) {
        subUrl = properties.getProperty("logOut") + deviceId;
        AndroidNetworking.get(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void getRequestCategory(final APIResponse listener) {
        subUrl = properties.getProperty("requestCategory");
        AndroidNetworking.get(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void getAllRequest(final APIResponse listener, String adminId, String segment) {
        subUrl = properties.getProperty("allRequest") + "/" + adminId + "/" + segment;
        AndroidNetworking.get(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void getEventByAdmin(final APIResponse listener, String adminId, String segment) {
        subUrl = properties.getProperty("eventByAdmin") + adminId + "/" + segment;
        AndroidNetworking.get(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void getEventByManagerStaff(final APIResponse listener, String user_id, String role, String segment) {
        subUrl = properties.getProperty("events") + segment;
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("user_id", user_id)
                .addBodyParameter("role", role)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void getEventDetails(final APIResponse listener, String id) {
        subUrl = properties.getProperty("eventDetail") + id;
        AndroidNetworking.get(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void getRequestDetails(final APIResponse listener, String requestId) {
        subUrl = properties.getProperty("requestDetails") + requestId;
        AndroidNetworking.get(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void addRequest(final APIResponse listener, String title, String category_id, String description, String quantity, String temple_id, String createdBy) {

        subUrl = properties.getProperty("addRequest");

        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("title", title)
                .addBodyParameter("category_id", category_id)
                .addBodyParameter("description", description)
                .addBodyParameter("quantity", quantity)
                .addBodyParameter("temple_id", temple_id)
                .addBodyParameter("createdBy", createdBy)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void addComment(final APIResponse listener, String request_id, String comment, String admin, File file) {

        subUrl = properties.getProperty("addComment");
        if (file == null) {
            //           Log.e("Tag", "file null");
            AndroidNetworking.post(mainUrl + subUrl)
                    .addHeaders("Auth-Key", GlobalApplication.auth)
                    .addBodyParameter("request_id", request_id)
                    .addBodyParameter("comment", comment)
                    .addBodyParameter("admin", admin)
                    .addBodyParameter("file", "")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            listener.onSuccess(response);
                        }

                        @Override
                        public void onError(ANError anError) {
                            listener.onFailure(anError.getErrorBody());
                        }
                    });
        } else {
            AndroidNetworking.upload(mainUrl + subUrl)
                    .addHeaders("Auth-Key", GlobalApplication.auth)
                    .addMultipartParameter("request_id", request_id)
                    .addMultipartParameter("comment", comment)
                    .addMultipartParameter("admin", admin)
                    .addMultipartFile("file", file)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            listener.onSuccess(response);
                        }

                        @Override
                        public void onError(ANError anError) {
                            listener.onFailure(anError.getErrorBody());
                        }
                    });
        }
    }

    public void changeRequestStatus(final APIResponse listener, String request_id) {

        subUrl = properties.getProperty("changeRequestStatus");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("request_id", request_id)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void allviewproduct(final APIResponse listener, String temple_id, String segment) {
        subUrl = properties.getProperty("products");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("temple_id", temple_id)
                .addBodyParameter("segment", segment)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("~~~", "   : " + response);
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {

                        Log.d("~~~", "   : " + anError.getMessage());
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void viewaalreturn(final APIResponse listener, String temple_id, String segment) {
        subUrl = properties.getProperty("returns");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("temple_id", temple_id)
                .addBodyParameter("segment", segment).setPriority(Priority.MEDIUM)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("~~~", "   : " + response);
                listener.onSuccess(response);
            }

            @Override
            public void onError(ANError anError) {
                Log.d("~~~", "   : " + anError.getMessage());
                listener.onFailure(anError.getErrorBody());
            }
        });
    }

    public void viewdistribution(APIResponse listener, String temple_id, String segment) {
        subUrl = properties.getProperty("distributes");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("temple_id", temple_id)
                .addBodyParameter("segment", segment).
                setPriority(Priority.MEDIUM)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("~~~", "   : " + response);
                listener.onSuccess(response);
            }

            @Override
            public void onError(ANError anError) {
                Log.d("~~~", "   : " + anError.getMessage());
                listener.onFailure(anError.getErrorBody());
            }
        });
    }

    public void ViewPos(APIResponse listener, String temple_id, String segment) {
        subUrl = properties.getProperty("Polist");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("temple_id", temple_id)
                .addBodyParameter("segment", segment).
                setPriority(Priority.MEDIUM)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("~~~", "   : " + response);
                listener.onSuccess(response);
            }

            @Override
            public void onError(ANError anError) {
                Log.d("~~~", "   : " + anError.getMessage());
                listener.onFailure(anError.getErrorBody());
            }
        });
    }

    public void distributionmanagerlist(APIResponse listener, String temple_id) {
        subUrl = properties.getProperty("distributesmanager");
        Log.d("~~~", "   : " + mainUrl + subUrl);
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("temple_id", temple_id)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("~~~", "   : " + response);
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("~~~", "   : " + anError);
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }


    public void distributeproductlist(final APIResponse listener, String temple_id) {
        subUrl = properties.getProperty("distributeproductlist");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("temple_id", temple_id)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("~~~", "   : " + response);
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {

                        Log.d("~~~", "   : " + anError.getMessage());
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }


    public void addproduct(final APIResponse listener, String templeId, String userId, String name, String picture_path, String type, String quantity) {
        subUrl = properties.getProperty("addproduct");
        Log.d("~~~", "Enter API    ");
        AndroidNetworking.upload(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addMultipartParameter("temple_id", templeId)
                .addMultipartParameter("user_id", userId)
                .addMultipartParameter("name", name)
                .addMultipartParameter("qty", quantity)
                .addMultipartParameter("type", type)
                .addMultipartFile("image", new File(picture_path))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                        Log.d("~~~", "response 1   " + response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());

                    }
                });
    }


    public void addDistributeproductdata(APIResponse listener, String templeId, String userId, String product_id, String deparmenet_id, String manager_id, String quantity, String personname, String mobileno, String department_name) {

        subUrl = properties.getProperty("adddistribute");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("temple_id", templeId)
                .addBodyParameter("user_id", userId)
                .addBodyParameter("product_id", product_id)
                .addBodyParameter("department_id", deparmenet_id)
                .addBodyParameter("manager_id", manager_id)
                .addBodyParameter("qty", quantity)
                .addBodyParameter("txtCarrierPersonName", personname)
                .addBodyParameter("txtCarrierMobile", mobileno)
                .addBodyParameter("txtPersonName", department_name)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void returnproductmanagerdata(final APIResponse listener, String temple_id) {
        subUrl = properties.getProperty("addreturnproducts");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("temple_id", temple_id)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("~~~", "   : " + response);
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {

                        Log.d("~~~", "   : " + anError.getMessage());
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }

    public void addDReturnproductdata(APIResponse listener, String templeId, String userId, String product_id, String deparmenet_id, String manager_id, String quantity, String personname, String mobileno, String department_name, String des) {

        subUrl = properties.getProperty("addreturn");
        AndroidNetworking.post(mainUrl + subUrl)
                .addHeaders("Auth-Key", GlobalApplication.auth)
                .addBodyParameter("temple_id", templeId)
                .addBodyParameter("user_id", userId)
                .addBodyParameter("product_id", product_id)
                .addBodyParameter("department_id", deparmenet_id)
                .addBodyParameter("manager_id", manager_id)
                .addBodyParameter("qty", quantity)
                .addBodyParameter("txtCarrierPersonName", personname)
                .addBodyParameter("txtCarrierMobile", mobileno)
                .addBodyParameter("txtPersonName", department_name)
                .addBodyParameter("description", des)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailure(anError.getErrorBody());
                    }
                });
    }


}
