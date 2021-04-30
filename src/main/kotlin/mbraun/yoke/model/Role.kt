package mbraun.yoke.model

import com.google.common.collect.Sets
import mbraun.yoke.model.Permissions.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class Role(val permissions: Set<Permissions>) {
    PRIMARY_OWNER(
        Sets.newHashSet(
            //File management
            VIEW_FILES,
            DOWNLOAD_FILES,
            DOWNLOAD_CSV_LISTS,
            CREATE_FOLDERS,
            UPLOAD_FILES_FOLDERS,
            MOVE_FILES_FOLDERS,
            COPY_FILES_FOLDERS,
            RENAME_FILES_FOLDERS,
            DELETE_FILES_FOLDERS,
            SHARE_FOLDERS,
            CREATE_MANAGE_PROFILES,
            EMPTY_TRASH,
            LOCK_UNLOCK_FOLDERS,
            CHANGE_SETTINGS_FOLDERS,

            //Connectivity
            WEB_APP_ACCESS,
            REST_API_ACCESS,

            //Account management
            VIEW_ANALYTICS,
            ACCOUNT_EMAIL_ALERTS,
            CHANGE_ACCOUNT_NAME,
            EDIT_ACCOUNT_DETAILS,
            TRANSFER_ACCOUNT_OWNERSHIP,
            CLOSE_ACCOUNT,

            //User management
            INVITE_NEW_USER,
            REMOVE_USER,
            APPOINT_ADMINS,
            DEMOTE_ADMINS,
            APPOINT_OWNERS,
            DEMOTE_OWNERS,

            //Billing
            VIEW_INVOICES,
            CHANGE_BILLING_PLAN,
            MANAGE_PAYMENT_METHOD,
            MANAGE_BILLING_DETAILS,
            BILLING_EMAIL_ALERTS
        )),
        OWNER(Sets.newHashSet(
            //File management
            VIEW_FILES,
            DOWNLOAD_FILES,
            DOWNLOAD_CSV_LISTS,
            CREATE_FOLDERS,
            UPLOAD_FILES_FOLDERS,
            MOVE_FILES_FOLDERS,
            COPY_FILES_FOLDERS,
            RENAME_FILES_FOLDERS,
            DELETE_FILES_FOLDERS,
            SHARE_FOLDERS,
            CREATE_MANAGE_PROFILES,
            EMPTY_TRASH,
            LOCK_UNLOCK_FOLDERS,
            CHANGE_SETTINGS_FOLDERS,

            //Connectivity
            WEB_APP_ACCESS,
            REST_API_ACCESS,

            //Account management
            VIEW_ANALYTICS,
            ACCOUNT_EMAIL_ALERTS,
            CHANGE_ACCOUNT_NAME,
            EDIT_ACCOUNT_DETAILS,

            //User management
            INVITE_NEW_USER,
            REMOVE_USER,
            APPOINT_ADMINS,
            DEMOTE_ADMINS,
            APPOINT_OWNERS,
            DEMOTE_OWNERS,

            //Billing
            VIEW_INVOICES,
            CHANGE_BILLING_PLAN,
            MANAGE_PAYMENT_METHOD,
            MANAGE_BILLING_DETAILS,
            BILLING_EMAIL_ALERTS
        )),
        ADMIN(Sets.newHashSet(
            //File management
            VIEW_FILES,
            DOWNLOAD_FILES,
            DOWNLOAD_CSV_LISTS,
            CREATE_FOLDERS,
            UPLOAD_FILES_FOLDERS,
            MOVE_FILES_FOLDERS,
            COPY_FILES_FOLDERS,
            RENAME_FILES_FOLDERS,
            DELETE_FILES_FOLDERS,
            SHARE_FOLDERS,
            CREATE_MANAGE_PROFILES,
            EMPTY_TRASH,
            LOCK_UNLOCK_FOLDERS,
            CHANGE_SETTINGS_FOLDERS,

            //Connectivity
            WEB_APP_ACCESS,
            REST_API_ACCESS,

            //Account management
            VIEW_ANALYTICS,
            ACCOUNT_EMAIL_ALERTS,

            //User management
            INVITE_NEW_USER,
            REMOVE_USER,
            APPOINT_ADMINS,
            DEMOTE_ADMINS,
        )),
        EDITOR(Sets.newHashSet(
            //File management
            VIEW_FILES,
            DOWNLOAD_FILES,
            DOWNLOAD_CSV_LISTS,
            CREATE_FOLDERS,
            UPLOAD_FILES_FOLDERS,
            MOVE_FILES_FOLDERS,
            COPY_FILES_FOLDERS,
            RENAME_FILES_FOLDERS,
            DELETE_FILES_FOLDERS,
            SHARE_FOLDERS,
            CREATE_MANAGE_PROFILES,

            //Connectivity
            WEB_APP_ACCESS,
        )),
        CONTRIBUTOR(Sets.newHashSet(
            //File management
            VIEW_FILES,
            DOWNLOAD_FILES,
            DOWNLOAD_CSV_LISTS,
            CREATE_FOLDERS,
            UPLOAD_FILES_FOLDERS,

            //Connectivity
            WEB_APP_ACCESS,
        )),

        VIEWER(Sets.newHashSet(
            //File management
            VIEW_FILES,
            DOWNLOAD_FILES,
            DOWNLOAD_CSV_LISTS,

            //Connectivity
            WEB_APP_ACCESS,

        )),
        BILLING(Sets.newHashSet(
            //File management
            VIEW_FILES,
            DOWNLOAD_FILES,
            DOWNLOAD_CSV_LISTS,

            //Connectivity
            WEB_APP_ACCESS,

            //Billing
            VIEW_INVOICES,
            CHANGE_BILLING_PLAN,
            MANAGE_PAYMENT_METHOD,
            MANAGE_BILLING_DETAILS,
            BILLING_EMAIL_ALERTS
        ));

    fun getGrantedAuthorities() : Set<GrantedAuthority> {
        val permissions = permissions.asSequence().map { permission -> SimpleGrantedAuthority(permission.permission) }.toMutableSet()

        permissions.add(SimpleGrantedAuthority("ROLE_${this.name}"))

        return permissions
    }
}