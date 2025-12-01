package com.example.flowersshop.data

import com.example.flowersshop.data.InitSupabaseClient.client
import io.github.jan.supabase.postgrest.postgrest

class GetBouquets {
    suspend fun execute(): List<Bouquet> {
        return client.postgrest["bouquets"].select().decodeList<Bouquet>()
    }
}