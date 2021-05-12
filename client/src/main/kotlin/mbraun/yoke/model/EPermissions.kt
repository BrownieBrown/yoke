package mbraun.yoke.model

enum class EPermissions(private val permission: String) {
    // File Management
    VIEW_FILES("files:view"),
    DOWNLOAD_FILES("files:download"),
    DOWNLOAD_CSV_LISTS("csv_list:download"),
    CREATE_FOLDERS("folders:create"),
    UPLOAD_FILES_FOLDERS("files/folders:upload"),
    MOVE_FILES_FOLDERS("files/folders:move"),
    COPY_FILES_FOLDERS("files/folders:copy"),
    RENAME_FILES_FOLDERS("files/folders:rename"),
    DELETE_FILES_FOLDERS("files/folders:delete"),
    SHARE_FOLDERS("folders:share"),
    CREATE_MANAGE_PROFILES("profiles:create/manage"),
    EMPTY_TRASH("trash:empty"),
    LOCK_UNLOCK_FOLDERS("folders:lock/unlock"),
    CHANGE_SETTINGS_FOLDERS("folders:change_settings"),

    //Connectivity
    WEB_APP_ACCESS("web_app:access"),
    REST_API_ACCESS("rest_api:access"),

    //Account management
    VIEW_ANALYTICS("analytics:view"),
    ACCOUNT_EMAIL_ALERTS("account_email:alerts"),
    CHANGE_ACCOUNT_NAME("account_name:change"),
    EDIT_ACCOUNT_DETAILS("account_details:edit"),
    TRANSFER_ACCOUNT_OWNERSHIP("account_ownership:transfer"),
    CLOSE_ACCOUNT("account:close"),

    //User Management
    INVITE_NEW_USER("new_user:invite"),
    REMOVE_USER("user:remove"),
    APPOINT_ADMINS("admins:appoint"),
    DEMOTE_ADMINS("admins:demote"),
    APPOINT_OWNERS("owners:appoint"),
    DEMOTE_OWNERS("owners:demote"),

    //Billing
    VIEW_INVOICES("invoices:view"),
    CHANGE_BILLING_PLAN("billing_plan:change"),
    MANAGE_PAYMENT_METHOD("payment_method:manage"),
    MANAGE_BILLING_DETAILS("billing_details:manage"),
    BILLING_EMAIL_ALERTS("billing_emails:alert");

    fun getPermission(): String {
        return permission
    }
}