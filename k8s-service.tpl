apiVersion: v1
kind: Service
metadata:
 name: {APP_NAME}-service
spec:
 type: NodePort
 ports:
  - port: 8082
    nodePort: 32001
 selector:
   app: {APP_NAME}