while true; do
  echo "GET /api/executar-ambos"
  curl "http://localhost:8080/api/executar-ambos?segundos1=1&segundos2=1"
done
