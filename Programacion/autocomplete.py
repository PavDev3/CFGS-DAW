# autocomplete.py
import requests
import sys
def get_suggestion(code):
    response = requests.post("http://localhost:11434/api/generate", 
        json={"model": "codellama", "prompt": f"Complete this Java code:\n{code}"})
    return response.json()["response"]
if __name__ == "__main__":
    code = sys.argv[1] if len(sys.argv) > 1 else ""
    print(get_suggestion(code))