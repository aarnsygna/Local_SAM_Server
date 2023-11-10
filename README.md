# Local_SAM_Server
 Local SAM Server implementation from SAM API built using FastAPI
 Running on MacOS implementation
Please refer to https://github.com/ksugar/samapi

### Non-conda install
    1. Create python venv: *python3 -m venv samapi*
    2. Activate environemnt: source samapi/bin/activate
    3. Upgrade pip: pip install --upgrade pip
    4. Install from git: pip install git+https://github.com/ksugar/samapi.git
    5. [Optional] To update: pip install --upgrade git+https://github.com/ksugar/samapi.git
    6. Use: 
            export PYTORCH_ENABLE_MPS_FALLBACK=1 # Required for running on Apple silicon
            uvicorn samapi.main:app --workers 2
            #First time will download & install models (Huge-mobile)