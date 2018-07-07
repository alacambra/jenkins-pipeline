def apply(String file, String namespace) {
    sh "kubectl apply --namespace=$namespace -f $file"
}

def rolloutStatus(String deployment, String namespace) {
    sh "kubectl rollout status --namespace=$namespace deployment $deployment"
}
