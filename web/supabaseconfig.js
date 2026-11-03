// filepath: supabaseConfig.js

// Substitua pelos seus valores do Supabase
const SUPABASE_URL = "https://gxjkjjtvalgighriuozb.supabase.co";
const SUPABASE_KEY = "sb_publishable_GkyxLL0Vkdp3JA08zaWKHQ_dSctyGeU";

// Inicializar o cliente do Supabase
const supabase = supabase.createClient(SUPABASE_URL, SUPABASE_KEY);

// Exportar o cliente para ser usado em outros arquivos
export default supabase;