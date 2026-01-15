package com.beast.adblock

import android.net.VpnService
import android.os.ParcelFileDescriptor
import android.util.Log

/* এই সার্ভিসটি রুট ছাড়াই ফোনের সব অ্যাড রিকোয়েস্ট লোকাল ভিপিএন দিয়ে ফিল্টার করবে */
class BeastVpnService : VpnService() {
    private var vpnInterface: ParcelFileDescriptor? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val builder = Builder()
        builder.setSession("The Beast")
            .addAddress("10.8.0.2", 32)
            .addDnsServer("1.1.1.1") // Cloudflare DoH connection point
            .addRoute("0.0.0.0", 0)
            .setBlocking(true)

        try {
            vpnInterface = builder.establish()
            Log.d("BEAST", "VPN Started Successfully")
        } catch (e: Exception) {
            Log.e("BEAST", "VPN Start Failed: ${e.message}")
        }
        return START_STICKY
    }

    override fun onDestroy() {
        vpnInterface?.close()
        super.onDestroy()
    }
}
