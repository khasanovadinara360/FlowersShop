package com.example.flowersshop.data

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage


object InitSupabaseClient {
    val client = createSupabaseClient("https://usbtrvwvcopzwhjczmzh.supabase.co",
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVzYnRydnd2Y29wendoamN6bXpoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjIyNzU2NTksImV4cCI6MjA3Nzg1MTY1OX0.oAp1v6YLGBpEm2jkxQCAcbzzmPtWncZ9mu7nf6DjSHs") {
        install(Postgrest)
        install(Auth)
        install(Realtime)
        install(Storage)
    }
}