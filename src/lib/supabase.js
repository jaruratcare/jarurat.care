import { createClient } from '@supabase/supabase-js'

const supabaseUrl = "https://peygbqadbyvsowhzdheq.supabase.co"

const supabaseKey = "sb_publishable_7v4u3kv_m0b1nhvZJK97vg_8ZNNCwij"

export const supabase = createClient(supabaseUrl, supabaseKey)