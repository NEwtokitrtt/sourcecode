package com.cashluto.rewad;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PackageChecker {
    private final Context context;

    // Constructor to initialize the context
    public PackageChecker(Context context) {
        this.context = context;
    }

    // Method to check if any specified package is installed and launch the appropriate activity
    public void checkPackagesAndLaunchActivity() {
        // List of package names to check for cloning apps
        List<String> packageNames = new ArrayList<>();
        packageNames.add("com.reqable.android");
        packageNames.add("com.lbe.parallel.intl");
        packageNames.add("com.doublenetwork.dualspace");
        packageNames.add("com.applisto.appcloner");
        packageNames.add("com.lbe.parallel.intl");
        packageNames.add("com.fuzhou.number1app");
        packageNames.add("com.oasisfeng.island");
        packageNames.add("com.app.clone");
        packageNames.add("com.dualspace.app");
        packageNames.add("com.spare.app");
        packageNames.add("com.gomultiple");
        packageNames.add("com.devdroid.cloner");
        packageNames.add("com.parallel.space");
        packageNames.add("com.superclone.app");
        packageNames.add("com.asiainno.cloneit");
        packageNames.add("com.multiaccounts");
        packageNames.add("com.doublenetwork.dualspace.lite");
        packageNames.add("com.doublenetwork.dualspace.pro");
        packageNames.add("com.dualmaster");
        packageNames.add("com.samsung.android.app.twin");
        packageNames.add("com.parallelspace.multiaccounts");
        packageNames.add("com.clonapp.app");
        packageNames.add("com.lbe.parallel.pro");
        packageNames.add("com.applisto.appcloner.premium");
        packageNames.add("com.tandem.app");
        packageNames.add("com.accountswitcher");
        packageNames.add("com.masterclone.app");
        packageNames.add("com.pro.cloner");
        packageNames.add("com.clonapp.messenger");
        packageNames.add("com.multialt");
        packageNames.add("com.oppo.app.twin");
        packageNames.add("com.xiaomi.xshare");
        packageNames.add("com.bluestacks");
        packageNames.add("com.memuplay.memu");
        packageNames.add("com.devhd.HttpCanary");
        packageNames.add("com.reqable.android");
        packageNames.add("com.security.packetcapture");
        packageNames.add("com.foobnix.netcapture");
        packageNames.add("com.xj.android.charlesproxy");
        packageNames.add("com.nostalgia.tpacketcapture");
        packageNames.add("com.zephyrnet.netflow");
        packageNames.add("com.xtremelabs.sslcapture");
        packageNames.add("org.shark.fderr");
        packageNames.add("com.northwave.android.sniffer");
        packageNames.add("com.tinyvpn.pktcapture");
        packageNames.add("com.packetcapture.android");
        packageNames.add("com.dpnss.packetcapture");
        packageNames.add("com.networkanalyzer.wifi");
        packageNames.add("com.ewan.httppacketcapture");
        packageNames.add("com.tcpdump.capture");
        packageNames.add("com.mqttclient.packetcapture");
        packageNames.add("com.android.packetcapture");
        packageNames.add("com.netcapture.sniffer");
        packageNames.add("com.tandem.packetcapture");
        packageNames.add("com.vesper.networkmonitor");

        boolean isPackageFound = false;

        // Check if any of the specified packages are installed
        for (String packageName : packageNames) {
            if (isPackageInstalled(packageName)) {
                isPackageFound = true;
                // If a package is found, launch the SecondActivity
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("message", "Invaded app detected! This is not safe. Please uninstall it before using this app. ERROR:: Found: " + packageName);
                intent.putExtra("PKG", packageName);
                context.startActivity(intent);
                break; // Exit loop after finding the first package
            }
        }

        // Additional checks for VPN apps
        if (!isPackageFound) {
            String vpnPackage = isVpnAppDetected();
            if (vpnPackage != null) {
                isPackageFound = true;
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("message", "VPN app detected! Please disable it before using this app.");
                intent.putExtra("PKG", vpnPackage);  // Pass the detected VPN package name
                context.startActivity(intent);
            }
        }

        // Additional check for Rooted device
        if (!isPackageFound && isDeviceRooted()) {
            isPackageFound = true;
            Intent intent = new Intent(context, SecondActivity.class);
            intent.putExtra("message", "Root access detected! Please unroot your device.");
            context.startActivity(intent);
        }

        // If no specified package, VPN, or root detected, launch MainActivity
        if (!isPackageFound) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("message", "No specified package found.");
            context.startActivity(intent);
        }
    }

    // Helper method to check if a package is installed
    private boolean isPackageInstalled(String packageName) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(packageName, 0);
            return packageInfo != null; // Package exists
        } catch (PackageManager.NameNotFoundException e) {
            return false; // Package not found
        }
    }

    // Helper method to detect VPN apps
    private String isVpnAppDetected() {
        List<String> vpnPackages = new ArrayList<>();  // Android default VPN service
        vpnPackages.add("com.nordvpn.android");    // NordVPN
        vpnPackages.add("org.openvpn.openvpn");    // OpenVPN
        vpnPackages.add("com.vpnsecure.android");  // VPN Secure
        vpnPackages.add("com.expressvpn.vpn");     // ExpressVPN
        vpnPackages.add("com.pia.android");        // Private Internet Access
        vpnPackages.add("com.turbo.vpn");          // Turbo VPN
        vpnPackages.add("com.vpnmaster");          // VPN Master
        vpnPackages.add("com.hideman.vpn");        // Hideman VPN
        vpnPackages.add("com.vpnfree.vpn");        // Free VPN
        vpnPackages.add("com.speedvpn");           // SpeedVPN
        vpnPackages.add("com.kpn.vpn");            // KPN VPN
        vpnPackages.add("com.avira.vpn");          // Avira Phantom VPN
        vpnPackages.add("com.shadowvpn");          // Shadow VPN
        vpnPackages.add("com.fastvpn");            // Fast VPN
        vpnPackages.add("com.tor.vpn");             // Tor VPN (sometimes can be used in VPN contexts)
        vpnPackages.add("com.vpnex");              // VPN Ex
        vpnPackages.add("com.yourvpn");            // Your VPN

        // Add more known VPN package names as needed

        for (String vpnPackage : vpnPackages) {
            if (isPackageInstalled(vpnPackage)) {
                return vpnPackage; // Return the detected VPN package name
            }
        }
        return null; // No VPN app detected
    }

    // Helper method to detect if the device is rooted
    private boolean isDeviceRooted() {
        String[] paths = {"/system/bin/su", "/system/xbin/su", "/system/app/Superuser.apk"};
        for (String path : paths) {
            if (new File(path).exists()) {
                return true; // Root access detected
            }
        }

        // Additional root checks can be added as needed (e.g., running "su" command)
        return false;
    }
}
